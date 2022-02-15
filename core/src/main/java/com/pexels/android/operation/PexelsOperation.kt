package com.pexels.android.operation

import com.pexels.android.operation.PhotosOperation

/**
 * A interface to make testing easier (Dependency Injection).
 * It comprises operations on Photos, Videos
 */
interface PexelsOperation : PhotosOperation, VideosOperation