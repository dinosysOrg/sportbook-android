package com.dinosys.sportbook.features.tournament.signup

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.SkillDataModel
import kotlinx.android.synthetic.main.item_skillset.view.*
import java.util.*


class SkillViewPagerAdapter(val skills: ArrayList<SkillDataModel>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val skill = skills.get(position)
        val skillView = LayoutInflater.from(container?.context).inflate(R.layout.item_skillset, null)
        with(skillView) {
            tvSkillTitle.text = skill.name
        }
        (container as ViewPager).addView(skillView)
        return skillView
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun getCount(): Int {
        return skills.size
    }
}