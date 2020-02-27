    package com.example.kotlintest

    import android.content.Context
    import android.graphics.Paint
    import android.util.Log
    import android.view.View
    import android.widget.Toast
    import kotlin.jvm.JvmStatic

    private val codeList = arrayOf(
        "kotlin",
        "java",
        "http"
    )

    private val intList = intArrayOf(
        10,
        5
    )

    private val floatList = floatArrayOf(
        1.3f,
        3.5f
    )

    fun updateCode() {
        100.toFloat()
    }

//  静态函数

    object CacheUtils {
    }


    data class User internal constructor(var name: String?, var age: Int) {
        constructor() : this(null, 0)

        private var mAge: Int = 0

        private var lessons: ArrayList<String> = ArrayList()

        fun updateAge(value: Int) {

            /**
             * 函数嵌套操作
             */
            fun updateAgeChild(value: Int) {
                mAge = value
            }

            mAge = when(value) {
                2,3 -> 5
                4 -> 6
                else -> 0
            }

            lessons.forEach {
                if (it == "abc") {
                    Log.d("string", it)
                }
                updateAgeChild(10)
            }

            lessons.filter {
                it == "abc"
            }

            repeat(100) {
                lessons.add("abc")
            }

            for (i in 0 until lessons.size) {
                lessons.add("abc")
            }
        }
    }

    class JvmStatic {

        private val user: User by lazy {
            User()
        }

        private val paint: Paint = Paint().apply {
            // 初始化之后直接设置其属性
            isAntiAlias = true
            strokeWidth = 6f.dp2px()
        }

//        private val user2: User = User().let {
//            // 初始化之后直接使用
//        }

        companion object {
            @JvmStatic
            lateinit var currentApplication: Context
                private set
        }
    }

    fun testDp(value: String?) {
        var px = 30f.dp2px()

        value?.let {
            // 使用let操作避免多线程操作问题
            // 如果不使用let需要先接收值来避免多线程
        }
    }


