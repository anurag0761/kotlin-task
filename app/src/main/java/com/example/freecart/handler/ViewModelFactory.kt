package com.example.freecart.handler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.freecart.ApiRepo.ApiRepo
import com.example.freecart.ProductViewModel.ApiViewModel
import com.logidtic.blueaid.utility.CoroutineHandler
import com.logidtic.blueaid.utility.PreferenceHandler


/**
 * Create ViewModelFactory for UserViewModel.
 *
 * @param preferenceHandler to handle preference actions.
 * @param coroutineHandler to handle coroutine action.
 * @param userRepo is the instance of user repo.
 *
 * @return instance of view model provider.
 */
@Suppress("UNCHECKED_CAST")
class ApiViewModelFactory(
    private val preferenceHandler: PreferenceHandler,
    private val coroutineHandler: CoroutineHandler,
    private val userRepo: ApiRepo
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel( preferenceHandler,coroutineHandler, userRepo,) as T
    }
}



/**
 * Create ViewModelFactory for UtilityViewModel.
 *
 * @param coroutineHandler to handle coroutine action.
 * @param utilityRepo is the instance of utility repo.
 *
 * @return instance of view model provider.
 */

