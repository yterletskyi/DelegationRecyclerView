package io.jasperapps.delegationrecyclerviewadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.jasperapps.delegationrecyclerviewadapter.core.RecyclerDelegationAdapter

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = RecyclerDelegationAdapter()
            .apply {
                addDelegate(
                    SampleTextDelegate1(),
                )
                addDelegate(
                    SampleTextDelegate2()
                )
                setItems(
                    (0..10).map {
                        if (it % 2 == 0) TextModelItem1("view type 1")
                        else TextModelItem2("view type", "2")
                    }
                )
            }


    }
}