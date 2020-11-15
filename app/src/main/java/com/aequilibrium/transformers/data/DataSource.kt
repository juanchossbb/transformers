package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.data.model.Transformer
import io.reactivex.Single

interface DataSource {

    fun retrieveToken() : Single<String>

    fun createTransformer(transformer : Transformer) : Single<Transformer>
}