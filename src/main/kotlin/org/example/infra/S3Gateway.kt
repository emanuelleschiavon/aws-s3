package org.example.infra

import com.amazonaws.services.s3.AmazonS3
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class S3Gateway(
    private val amazonS3: AmazonS3,
) {
    fun retrieveContent(bucket: String, name: String) =
        amazonS3.getObject(bucket, name).objectContent
            .let { String(it.readAllBytes(), StandardCharsets.UTF_8) }
}
