package ru.geekbrains

import android.app.Application
import ru.geekbrains.DI.AppComponent
import ru.geekbrains.DI.DaggerAppComponent

class App: Application() {


    lateinit var component: AppComponent

    companion object{
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder()
            .setContext(this)
            .build()

    }


//    override fun onCreate() {
//        super.onCreate()
//        appInstance = this
//    }

//    companion object Navigation {

//        private val cicerone: Cicerone<CustomRouter> by lazy {
//            Cicerone.create(CustomRouter())
//        }
//        val navigatorHolder = cicerone.getNavigatorHolder()
//        val router = cicerone.router
//
//
//        private var appInstance: App? = null
//        private var db: GitHubUserDb? = null
//        private const val DB_NAME = "GitHubUsers.db"
//
//        fun getHistoryDao(): GitHubUsersDao {
//            if (db == null) {
//                synchronized(GitHubUserDb::class.java) {
//                    if (db == null) {
//                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
//                        db = Room.databaseBuilder(
//                            appInstance!!.applicationContext,
//                            GitHubUserDb::class.java,
//                            DB_NAME)
//                            .allowMainThreadQueries()//Заменить на отдельный поток
//                            .build()
//                    }
//                }
//            }
//
//            return db!!.getGitHubUserDao()
//        }
//
//    }


}