package uz.gita.mirzabek.example.eventapp.presenter.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import uz.gita.mirzabek.example.eventapp.domain.usecase.AppUseCase
import uz.gita.mirzabek.example.eventapp.presenter.viewmodels.EventScreenViewModel
import javax.inject.Inject

@HiltViewModel
class EventScreenViewModelImpl @Inject constructor(
    val useCase: AppUseCase
) :EventScreenViewModel,ViewModel(){
    override val allEvents= MutableSharedFlow<List<EventModel>>()



    override fun clickEvent(eventModel: EventModel) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.updateEvent(eventModel)
        }
    }

    override fun getAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllEvents().collectLatest {
                allEvents.emit(it)
            }
        }
    }
}