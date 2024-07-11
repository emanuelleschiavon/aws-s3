package org.example.inbound

import org.example.domain.File
import org.example.infra.S3Gateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class FileController(
    private val s3Gateway: S3Gateway,
) {
    @PostMapping("/files/retrieve-content")
    fun retrieveContentInFile(@RequestBody file: File) =
        s3Gateway.retrieveContent(file.bucket, file.name)
            .let { ResponseEntity(it, HttpStatus.ACCEPTED) }
}
