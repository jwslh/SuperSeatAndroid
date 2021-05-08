package indi.wkq.superseatandroid.utils

import indi.wkq.superseatandroid.model.response.JsonData

/**
 * @author  calesq
 * @date    2021/5/8
 */
interface ICallback {
    open fun showLoading()

    open fun hideLoading()
}