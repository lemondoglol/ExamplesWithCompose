package com.example.examplewithcompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.PlayerView
import com.example.examplewithcompose.R
import com.example.examplewithcompose.coroutine_setup.ApplicationCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"

@Module
/**
 * all singleton in this module,
 * it lives as long as app is alive, decides lifetime, not same as @Singleton
 * */
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideApplicationCoroutineScope(): ApplicationCoroutineScope =
        ApplicationCoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    /**
     * https://medium.com/androiddevelopers/datastore-and-dependency-injection-ea32b95704e3
     * template code to create PreferencesDataStore
     * */
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext,USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    /**
     * ExoPlayer
     * */
    @Singleton
    @Provides
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        // auto adjust video quality based on network bandwidth
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        return ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
    }
}