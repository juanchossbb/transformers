package com.aequilibrium.transformers.data

import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.data.model.TransformerListResponse
import io.reactivex.Single

interface DataSource {

    fun retrieveToken() : Single<String>

    fun createTransformer(transformer : Transformer) : Single<Transformer>

    fun retrieveTransformerList() : Single<TransformerListResponse>

    fun removeTransformer(transformer: Transformer) : Single<Int>

    fun editTransformer(transformer : Transformer) : Single<Transformer>
}