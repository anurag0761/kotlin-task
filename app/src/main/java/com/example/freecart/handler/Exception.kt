package com.logidtic.blueaid.utility

import java.io.IOException

class NoNetworkException(message: String = UtilData.NO_INTERNET_MESSAGE): IOException(message)