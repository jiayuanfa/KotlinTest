package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url: String = "https://api.github.com/users/octocat/repos"
        okhttp3(url)
    }

    /**
     * okhttp3实例
     */
    fun okhttp3(url: String) {
        // OkHttp3
        val client: OkHttpClient = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    println("Response code is : " + response.code())
                }
            })
    }

    /**
     * Retrofit实例
     */
    fun retrofit() {
        // 创建 Retrofit 实例
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service = retrofit.create<GithubService>(GithubService::class.java)

        // 执行
        val repos = service.listRepos("octocat")
        repos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                println("response: " + t.message)
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                println("response: " + response.body()!![0].name)
            }

        })

        // RxJava
        val reposRx: Observable<List<Repo>> = service.listReposRx("octocat")
        reposRx.subscribeOn(Schedulers.io())
            .subscribe(object : Observer<List<Repo>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(value: List<Repo>?) {
                }

                override fun onError(e: Throwable?) {
                }
            })

        val reposRxSingle: Single<List<Repo>> = service.listReposSingle("octocat")
        reposRxSingle.subscribeOn(Schedulers.io())
            .subscribe(object: SingleObserver<List<Repo>> {
                override fun onSuccess(value: List<Repo>?) {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onError(e: Throwable?) {
                }

            })
    }
}
