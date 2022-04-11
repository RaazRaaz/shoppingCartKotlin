package com.project.shoppingcartkotlin.viewmodel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.shoppingcartkotlin.model.Data
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.repositary.Repository
import com.project.shoppingcartkotlin.repositary.RoomRepo
import kotlinx.coroutines.*

class ProductViewModel constructor(private val mainRepository: Repository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val productList = MutableLiveData<Data>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllProducts() {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = mainRepository.getAllProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    productList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


    var productLive: LiveData<List<ProductModel>>? = null

    fun insertData(context: Context, productModel: ProductModel) {
        RoomRepo.insertData(context, productModel)
    }

    fun getDbProduct(context: Context) : LiveData<List<ProductModel>>?{
        productLive = RoomRepo.getAllData(context)
        return productLive
    }

    fun updateData(context: Context, quantity:String, productId:String) {
        RoomRepo.updateData(context,quantity,productId)
    }

    fun deleteData(context: Context, id:Int?) {
        RoomRepo.deleteData(context, id!!)
    }

}



