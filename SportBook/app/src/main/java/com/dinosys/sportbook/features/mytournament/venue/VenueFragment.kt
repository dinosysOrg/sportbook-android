package com.dinosys.sportbook.features.mytournament.venue

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ConfirmDialog
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.configs.TIME_SLOT_MIN_REQUIRED
import com.dinosys.sportbook.exceptions.RequireFieldsMissingException
import com.dinosys.sportbook.exceptions.UnauthorizedException
import com.dinosys.sportbook.exceptions.UpdateTimeSlotFailure
import com.dinosys.sportbook.exceptions.UpdateTimeSlotMissingFeild
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.venue.input.InputTimeAdapter
import com.dinosys.sportbook.features.mytournament.venue.input.OnTimeBlocksListener
import com.dinosys.sportbook.features.mytournament.venue.model.TimeSlotUIModel
import com.dinosys.sportbook.networks.models.TimeBlockModel
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_tournament_venue.*
import org.json.JSONObject
import retrofit2.Response
import java.lang.ref.WeakReference
import javax.inject.Inject

class VenueFragment : BaseFragment(), OnTimeBlocksListener, ConfirmDialog.ConfirmDialogListener {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_venue

    var timeSlotList: ArrayList<TimeSlotUIModel>? = null

    var timeVenueAdapter: InputTimeAdapter? = null

    var tournamentDetail: TournamentDetailDataModel? = null

    init {
        RxBus.events(TournamentDetailDataModel::class.java)
                .subscribe { data -> tournamentDetail = data }
                .addDisposableTo(this)
    }

    @Inject
    lateinit var teamAPI: VenueViewModel

    override fun initViews() {
        timeSlotList = getTimeSlotUIListDefault()
        timeVenueAdapter = InputTimeAdapter(timeSlotList, WeakReference(this))
        rvTimeVenue.adapter = timeVenueAdapter
        rvTimeVenue.layoutManager = LinearLayoutManager(context)
        disableUIIfAlreadyUpdatedTimeVenue()
    }

    override fun initData() {
        SportbookApp.teamComponent.inject(this)
        loadTimeBlocks()
    }

    override fun initListeners() {
        super.initListeners()
        btnUpdateTimeVenue.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun loadTimeBlocks() {
        val teamId = tournamentDetail?.teams?.id
        if (teamId == null) {
            LogUtil.e(TAG, "[loadTimeBlocks] teamId is null")
            return
        }
        teamAPI.getTimeBlocks(teamId = teamId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { response -> onLoadTimeBlockSuccessfully(response) },
                    { throwable: Throwable? ->  onLoadTimeBlockFailure(throwable)}
                ).addDisposableTo(this)
    }

    private fun onLoadTimeBlockSuccessfully(response: Response<TimeBlockModel>) {
        when (response.code()) {
            in 200..300 -> {
                val timeBlock = response.body()
                if (timeBlock == null) {
                    onLoadTimeBlockFailure(NullPointerException("Timeblock is null"))
                } else {
                    renderUIForTimeBlock(timeBlockModel = timeBlock)
                }
            }
            401 -> onLoadTimeBlockFailure(UnauthorizedException(response.message()))
            422 -> onLoadTimeBlockFailure(RequireFieldsMissingException(response.message()))
        }
    }

    private fun renderUIForTimeBlock(timeBlockModel: TimeBlockModel) {
        val preferredTimeBlocks = timeBlockModel.preferredTimeBlocks
        if (preferredTimeBlocks == null) {
            LogUtil.e(TAG, "[renderUIForTimeBlock] preferredTimeBlocks = null")
            return
        }
        val arrayDays = resources.getStringArray(R.array.array_time_range_days)
        renderUIForTimeBlockItem(arrayDays[0], preferredTimeBlocks.sunday)
        renderUIForTimeBlockItem(arrayDays[1], preferredTimeBlocks.monday)
        renderUIForTimeBlockItem(arrayDays[2], preferredTimeBlocks.tuesday)
        renderUIForTimeBlockItem(arrayDays[3], preferredTimeBlocks.wednesday)
        renderUIForTimeBlockItem(arrayDays[4], preferredTimeBlocks.thursday)
        renderUIForTimeBlockItem(arrayDays[5], preferredTimeBlocks.firday)
        renderUIForTimeBlockItem(arrayDays[6], preferredTimeBlocks.staturday)
    }

    private fun renderUIForTimeBlockItem(day: String, blocks: List<List<Int>>?) {
        blocks?.forEach { block ->
            val blockTime = getTimeBlockByHour(hour = block[0])
            updateTimeBlockToAdatper(day = day, blockTime = blockTime!!)
        }
    }

    private fun onLoadTimeBlockFailure(t: Throwable?) {
        LogUtil.e(TAG, "[onLoadTimeBlockFailure] error: ${t?.message}")
    }

    private fun disableUIIfAlreadyUpdatedTimeVenue() {
        val venueRankingList = tournamentDetail?.teams?.venueRanking
        btnUpdateTimeVenue.isEnabled = (venueRankingList == null || venueRankingList.isEmpty())
    }

    private fun showUpdateTimeSlotError() {
        llInputTimeSlotError.visibility = View.VISIBLE
    }

    private fun hideUpdateTimeSlotError() {
        llInputTimeSlotError.visibility = View.GONE
    }

    private fun doUpdateTimeSlot() {
        val timeSlotCount = getTimeSlotCount()
        if (timeSlotCount < TIME_SLOT_MIN_REQUIRED) {
            showUpdateTimeSlotError()
            LogUtil.e(TAG, "[doUpdateTimeSlot] timeSlotCount require > ${TIME_SLOT_MIN_REQUIRED} but it is ${timeSlotCount}")
            return
        }
        val timeSlotParams = preareTimeSlotRequestPrarams()
        val rankingVenueParams = preapreRankingVenueRequestParams()
        teamAPI.updateTimeSlot(timeSlotParams, rankingVenueParams, tournamentDetail!!.teams!!.id)
                .subscribeOn(Schedulers.newThread())
                .onErrorResumeNext { t: Throwable? -> onUpdateTimeSlotFailure(t) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> onUpdateTimeSlotSuccessfully(response)},
                        { t: Throwable? -> onUpdateTimeSlotFailure(t) }
                )
                .addDisposableTo(this)
    }

    private fun onUpdateTimeSlotSuccessfully(response: Response<JSONObject>) {
        when (response?.code()) {
            in 200..300 -> {
                fragmentManager.popBackStack(1)
            }
            401 -> onUpdateTimeSlotFailure(UnauthorizedException(getString(R.string.error_unauthorized_text)))
            405 -> onUpdateTimeSlotFailure(UpdateTimeSlotFailure(getString(R.string.error_update_time_slot_failure_text)))
            422 -> onUpdateTimeSlotFailure(UpdateTimeSlotMissingFeild(getString(R.string.error_missing_feild_text)))
            else -> onUpdateTimeSlotFailure(Exception(response.message()))
        }

    }

    private fun onUpdateTimeSlotFailure(t: Throwable?): Observable<Response<JSONObject>> {
        LogUtil.e(TAG, "onUpdateTimeSlotFailure ${t?.message}")
        return Observable.empty()
    }

    //TODO: Need implement when ranking venue api available
    private fun preapreRankingVenueRequestParams() = arrayListOf(1,2,3,4)


    private fun preareTimeSlotRequestPrarams(): HashMap<String, Any> {
        val timeSlotMap = HashMap<String, Any>()
        val arrayTimeRange = activity.resources.getStringArray(R.array.array_time_range_days)
        arrayTimeRange.forEach { day ->
            val arrayTimeBlock = ArrayList<Any>()
            timeSlotList?.filter { timeVenue -> !timeVenue.isHeader }
                    ?.filter { timeVenueUIModel -> timeVenueUIModel.isAvailableBlockTime(day) }
                    ?.forEach { timeVenueUIModel ->
                        val timeBlockItemJsonArray = convertTimeBlockToArray(timeVenueUIModel.timeBlock)
                        arrayTimeBlock.add(timeBlockItemJsonArray!!)
                    }
            if (!arrayTimeBlock.isEmpty()) {
                timeSlotMap.put(day.toLowerCase(), arrayTimeBlock)
            }
        }
        return timeSlotMap
    }
    
    private fun getTimeSlotCount(): Int {
        var total = 0
        timeSlotList?.filter { timeSlot -> !timeSlot.isHeader }
                ?.filter { timeSlot -> (timeSlot.blockTimeRangeList != null && timeSlot.blockTimeRangeList!!.isNotEmpty()) }
                ?.forEach { t: TimeSlotUIModel? -> total +=  t!!.blockTimeRangeList!!.size}
        return total
    }

    private fun convertTimeBlockToArray(timeBlock: String): ArrayList<Int>? {
        if (timeBlock == activity.getString(R.string.time_block_9am_12am_text)) {
            return arrayListOf(9,10,11)
        } else if (timeBlock == activity.getString(R.string.time_block_1pm_4pm_text)) {
            return arrayListOf(13,14,15)
        } else if (timeBlock == activity.getString(R.string.time_block_5pm_9pm_text)) {
            return arrayListOf(17,18,19,20)
        }
        return null
    }

    private fun getTimeBlockByHour(hour: Int): String? {
        when (hour) {
            in 9..11 -> return activity.getString(R.string.time_block_9am_12am_text)
            in 13..15 -> return activity.getString(R.string.time_block_1pm_4pm_text)
            in 17..20 -> return activity.getString(R.string.time_block_5pm_9pm_text)
        }
        return null
    }

    fun getTimeSlotUIListDefault(): ArrayList<TimeSlotUIModel> {
        val items = ArrayList<TimeSlotUIModel>()
        items.add(TimeSlotUIModel(isHeader = true))
        items.add(TimeSlotUIModel(timeBlock = getString(R.string.time_block_9am_12am_text)))
        items.add(TimeSlotUIModel(timeBlock =  getString(R.string.time_block_1pm_4pm_text)))
        items.add(TimeSlotUIModel(timeBlock = getString(R.string.time_block_5pm_9pm_text)))
        return items
    }

    override fun OnTimeBlockClick(day: String, blockTime: String) {
        if (btnUpdateTimeVenue.isEnabled) {
            updateTimeBlockToAdatper(day = day, blockTime = blockTime)
        }
    }

    private fun updateTimeBlockToAdatper(day: String, blockTime: String) {
        hideUpdateTimeSlotError()
        val timeSlotRow = timeSlotList?.filter {
            timeVenueUIModel -> timeVenueUIModel.timeBlock == blockTime
        }?.get(0)
        if (timeSlotRow == null) {
            LogUtil.e(TAG, "[updateTimeBlockToAdatper] timeVenue = NULL")
            return
        }
        if (timeSlotRow.blockTimeRangeList == null) {
            timeSlotRow.blockTimeRangeList = ArrayList<String>()
        }

        val blockTimeRangeList = timeSlotRow.blockTimeRangeList
        if (blockTimeRangeList!!.contains(day)) {
            blockTimeRangeList.remove(day)
        } else {
            blockTimeRangeList.add(day)
        }
        timeVenueAdapter?.notifyDataSetChanged()
    }

    private fun showConfirmDialog() {
        val confirmDialog = ConfirmDialog(rvTimeVenue.context)
        confirmDialog.titleText = getString(R.string.warning_update_timeslot_text)
        confirmDialog.okText = getString(R.string.ok_text)
        confirmDialog.cancelText = getString(R.string.wait_let_me_check_text)
        confirmDialog.onConfirmDialogListener = this
        confirmDialog.show()
    }

    override fun onConfirmDialogCallback(dialog: ConfirmDialog, isAccept: Boolean) {
        if (isAccept) {
            doUpdateTimeSlot()
        }
        dialog.dismiss()
    }

    companion object {
        val TAG: String = "VenueFragment"
    }

}