package com.alom.androidstudy2
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MemoRepository) : ViewModel() {

    private var _currentMemo = MutableStateFlow<List<Memo>>(emptyList())
    val currentMemo: StateFlow<List<Memo>> get() = _currentMemo.asStateFlow()

    init {

        loadMemoList()
    }

    fun loadMemoList() {
        viewModelScope.launch {
            val memos = withContext(Dispatchers.IO) {
                repository.getMemos()
            }
            _currentMemo.emit(memos)
        }
    }

    fun addMemo(memo: Memo) {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                repository.addMemo(memo)
            }

            val memos = repository.getMemos()

            _currentMemo.emit(memos)
        }
    }
}
