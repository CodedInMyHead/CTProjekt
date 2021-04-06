package com.example.ctprojekt.snake

import android.graphics.PointF

class Maths {
    companion object {
        /**
         * Find similarity of two vector represented in PointF
         */
        fun CosineSim(v1: PointF, v2: PointF): Double {
            return (v1.x * v2.x + v1.y * v2.y) / (VectorNorm(v1) *
                    VectorNorm(v2))
        }

        /**
         * @param rotateVector vector to rotate from original cartesian coordinate
         * @param x: original x cooordinate
         * @param y original y coordinate
         * @return the transposed PointF containing new coordinates x, y
         */

        fun RotatedCoordinate(x: Float, y: Float, rotateVector: PointF): PointF {

            val diag = VectorNorm(rotateVector)

            val sinOfCurrentMoveVec = rotateVector.y / diag
            val cosOfCurrentMoveVec = rotateVector.x / diag

            val dxTransposed = x * cosOfCurrentMoveVec - y * sinOfCurrentMoveVec
            val dyTransposed = x * sinOfCurrentMoveVec + y * cosOfCurrentMoveVec
            return PointF(dxTransposed.toFloat(), dyTransposed.toFloat())
        }



        fun VectorNorm(v: PointF): Double {
            return Math.sqrt((v.x * v.x + v.y * v.y).toDouble())

        }

        /**
         * return sin cos of a vector
         */
        fun VecSinCos(v: PointF): PointF {
            val diag = VectorNorm(v)
            return PointF((v.x / diag).toFloat(), (v.y / diag).toFloat())

        }

        fun RotateVec(v: PointF, deg: Double): PointF {

            return PointF(
                    (VectorNorm(v) * Math.cos(Math.toRadians(deg))).toFloat(),
                    (VectorNorm(v) * Math.sin(Math.toRadians(deg))).toFloat()
            )
        }

    }
}