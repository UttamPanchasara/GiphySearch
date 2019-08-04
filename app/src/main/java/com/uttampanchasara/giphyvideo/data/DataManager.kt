package com.uttampanchasara.giphyvideo.data

import com.uttampanchasara.giphyvideo.data.network.ApiHelper
import com.uttampanchasara.giphyvideo.data.prefs.PreferencesHelper

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
interface DataManager : DbHelper, PreferencesHelper, ApiHelper {
}