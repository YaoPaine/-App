package com.yaopaine.albumwx.widget

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/15/18
 */
open class SimpleMoveGestureDetector : MoveGestureDetector.OnMoveGestureListener {
    override fun onMoveEnd(detector: MoveGestureDetector?) {
    }

    override fun onMove(detector: MoveGestureDetector?): Boolean {
        return false
    }

    override fun onMoveBegin(detector: MoveGestureDetector?): Boolean {
        return true
    }
}