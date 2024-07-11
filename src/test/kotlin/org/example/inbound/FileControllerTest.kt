package org.example.inbound

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.example.domain.File
import org.example.infra.S3Gateway
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockKExtension::class)
class FileControllerTest {

    @InjectMockKs
    private lateinit var fileController: FileController

    @MockK
    private lateinit var s3Gateway: S3Gateway

    @Test
    fun `retrieve file content`() {
        val name = "test.txt"
        val bucket = "bucketName"
        val file = File(name, bucket)
        val content = "This is the content in file!"
        every { s3Gateway.retrieveContent(bucket, name) } returns content

        val result = fileController.retrieveContentInFile(file)

        assertEquals(ResponseEntity(content, HttpStatus.ACCEPTED), result)
    }
}