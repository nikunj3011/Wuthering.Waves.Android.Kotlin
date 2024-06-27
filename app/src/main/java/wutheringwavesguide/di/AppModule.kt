package wutheringwavesguide.di

import android.content.Context
import androidx.room.Room
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import wutheringwavesguide.MainViewModel
import wutheringwavesguide.api.NewsService
import wutheringwavesguide.db.NewsDatabase
import wutheringwavesguide.repository.CategoryRepository
import wutheringwavesguide.repository.NewsRepository
import wutheringwavesguide.repository.UserRepository
import wutheringwavesguide.ui.bookmarks.BookmarksViewModel
import wutheringwavesguide.ui.news.NewsViewModel
import wutheringwavesguide.ui.newsdetail.NewsDetailViewModel
import wutheringwavesguide.ui.search.SearchViewModel
import wutheringwavesguide.ui.settings.SettingsViewModel
import wutheringwavesguide.util.AD_ID
import wutheringwavesguide.util.DB_NAME
import wutheringwavesguide.util.NEWS_URL
import wutheringwavesguide.util.PREFERENCE_NAME
import wutheringwavesguide.util.REFRESH_TIMEOUT
import wutheringwavesguide.util.RateLimiter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wutheringwavesguide.HomeViewModel
import wutheringwavesguide.api.WutheringGuidesService
import wutheringwavesguide.repository.HomeRepository
import wutheringwavesguide.ui.character.CharacterViewModel
import wutheringwavesguide.ui.characterdetail.CharacterDetailsViewModel
import wutheringwavesguide.ui.echos.EchoViewModel
import wutheringwavesguide.ui.homedata.HomeDataViewModel
import wutheringwavesguide.ui.weapons.WeaponViewModel
import wutheringwavesguide.util.WUTHERINGGuide_URL
import java.util.concurrent.TimeUnit

val appModule = module {

    factory {
        AdRequest.Builder().build()
    }

    single {
        AdLoader.Builder(androidContext(), AD_ID)
    }

    single {
        Retrofit.Builder()
            .baseUrl(NEWS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(WUTHERINGGuide_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WutheringGuidesService::class.java)
    }

    single {
        Room.databaseBuilder(androidContext(), NewsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        val db = get<NewsDatabase>()
        db.newsDao()
    }

    single {
        val db = get<NewsDatabase>()
        db.categoryDao()
    }

//    single {
//        val db = get<NewsDatabase>()
//        db.videosDao()
//    }

    single {
        androidContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    single {
        RateLimiter<String>(REFRESH_TIMEOUT, TimeUnit.MINUTES)
    }

    single {
        NewsRepository(get(), get(), get(), get())
    }

    single {
        CategoryRepository(get(), get())
    }

    single {
        UserRepository(get())
    }

    single {
        HomeRepository(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        EchoViewModel(get())
    }

    viewModel {
        CharacterDetailsViewModel(get())
    }

    viewModel {
        HomeDataViewModel(get())
    }

    viewModel {
        CharacterViewModel(get())
    }

    viewModel {
        WeaponViewModel(get())
    }

    viewModel {
        NewsViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        BookmarksViewModel(get())
    }

    viewModel {
        NewsDetailViewModel(get())
    }

    viewModel {
        SettingsViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }

}