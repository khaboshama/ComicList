package com.khaled.comiclist.common

import com.khaled.comiclist.common.data.IBaseRepository

abstract class BaseUseCase<Repository : IBaseRepository>(val repository: Repository)