import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pizzaria.model.Produto
import com.google.gson.Gson

object CarrinhoManager {
    private const val PREFS_NAME = "CARRINHO_PREFS"
    private const val ITEM_COUNT_KEY = "ITEM_COUNT"

    private lateinit var prefs: SharedPreferences
    private val _itemCountInCart = MutableLiveData<Int>()
    val itemCountInCart: LiveData<Int>
        get() = _itemCountInCart

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        _itemCountInCart.value = getItemCountInCart()
    }

    fun getItemCountInCart(): Int {
        return prefs.getInt(ITEM_COUNT_KEY, 0)
    }

    fun incrementItemCountInCart() {
        val currentCount = _itemCountInCart.value ?: 0
        _itemCountInCart.value = currentCount + 1
        prefs.edit().putInt(ITEM_COUNT_KEY, currentCount + 1).apply()
    }

    fun decrementItemCountInCart() {
        val currentCount = _itemCountInCart.value ?: 0
        if (currentCount > 0) {
            _itemCountInCart.value = currentCount - 1
            prefs.edit().putInt(ITEM_COUNT_KEY, currentCount - 1).apply()
        }
    }

    // Função para resetar o contador de itens no carrinho
    fun resetItemCountInCart() {
        _itemCountInCart.value = 0
        prefs.edit().putInt(ITEM_COUNT_KEY, 0).apply()
    }
}