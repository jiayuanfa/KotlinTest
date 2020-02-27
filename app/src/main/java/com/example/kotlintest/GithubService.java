package com.example.kotlintest;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repo>> listReposRx(@Path("user") String user);

    @GET("users/{user}/repos")
    Single<List<Repo>> listReposSingle(@Path("user") String user);
}
