package com.example.reactiveprog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.reactiveprog.db.AppDatabase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.get

        //for Publish Subject -- If a student comes late to the lecture, then it learns normally from the time he enters
//        val professor = PublishSubject.create<String>()
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()

        //for Replay Subject -- If a students comes late to the class, then the professor repeats(replay) the whole lecture to him

//        val professor = ReplaySubject.create<String>()
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()

        //Behaviour Subject -- Get the most recent emitted data
//        val professor = BehaviorSubject.create<String>()
//        professor.subscribe(getFirstStudent())
//        professor.onNext("kotlin")
//        professor.onNext("java")
//        professor.onNext("c++")
//
//        professor.subscribe(getLateStudent())
//        professor.onNext("scala")
//        professor.onComplete()

        //Async Subject - Get the last data emitted
        val professor = AsyncSubject.create<String>()
        professor.subscribe(getFirstStudent())
        professor.onNext("kotlin")
        professor.onNext("java")
        professor.onNext("c++")
        professor.subscribe(getLateStudent())
        professor.onNext("scala")
        professor.onComplete()

    }


    private fun getLateStudent():Observer<String>{
        return object:Observer<String>{
            override fun onComplete() {
                Log.e("***","The lecture has ended for late student")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.e("***","Teaching late student "+t)

            }

            override fun onError(e: Throwable) {
            }

        }
    }

    private fun getFirstStudent():Observer<String>{
        return object:Observer<String>{
            override fun onComplete() {
                Log.e("***","The lecture has ended for first student")

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.e("***","Teaching first student "+t)

            }

            override fun onError(e: Throwable) {
            }

        }
    }
}
