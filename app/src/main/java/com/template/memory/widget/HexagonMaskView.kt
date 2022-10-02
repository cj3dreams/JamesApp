package com.template.memory.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.sqrt

class HexagonMaskView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var hexagonPath: Path? = null
    private var hexagonBorderPath: Path? = null
    private var mBorderPaint: Paint? = null

    init {
        hexagonPath = Path()
        hexagonBorderPath = Path()
        mBorderPaint = Paint()
        mBorderPaint!!.color = Color.WHITE
        mBorderPaint!!.strokeCap = Paint.Cap.ROUND
        mBorderPaint!!.strokeWidth = 50f
        mBorderPaint!!.style = Paint.Style.STROKE
    }

    private fun calculatePath(radius: Float) {
        val halfRadius = radius / 2f
        val triangleHeight = (sqrt(3.0) * halfRadius).toFloat()
        val centerX = measuredWidth / 2f
        val centerY = measuredHeight / 2f
        hexagonPath?.apply {
            reset()
            moveTo(centerX + radius, centerY)
            lineTo(centerX + halfRadius, centerY - triangleHeight)
            lineTo(centerX - halfRadius, centerY - triangleHeight)
            lineTo(centerX - radius, centerY)
            lineTo(centerX - halfRadius, centerY + triangleHeight)
            lineTo(centerX + halfRadius, centerY + triangleHeight)
            close()
        }
        invalidate()
    }

    public override fun onDraw(c: Canvas) {
        c.drawPath(hexagonBorderPath!!, mBorderPaint!!)
        c.clipPath(hexagonPath!!, Region.Op.INTERSECT)
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        super.onDraw(c)
    }

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
        calculatePath((width / 2f).coerceAtMost(height / 2f))
    }
}