package org.example.infra

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.S3Object
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.nio.charset.StandardCharsets
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class S3GatewayTest {

    @InjectMockKs
    private lateinit var s3Gateway: S3Gateway

    @MockK
    private lateinit var amazonS3: AmazonS3

    @Test
    fun `retrieve file content`() {
        val name = "test.txt"
        val bucket = "bucketName"
        val content = "Here is the content in file!"
        val inputStream = content.byteInputStream(StandardCharsets.UTF_8)
        val s3object = S3Object().also { it.setObjectContent(inputStream) }

        every { amazonS3.getObject(name, bucket) } returns s3object

        val result = s3Gateway.retrieveContent(name, bucket)

        assertEquals(content, result)
    }
}