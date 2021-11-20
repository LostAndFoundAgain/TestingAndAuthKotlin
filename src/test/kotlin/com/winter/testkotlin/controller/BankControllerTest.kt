package com.winter.testkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.winter.testkotlin.model.Bank
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return all banks`(){
        // when/then
        mockMvc.get("/api/banks")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$[0].accountNumber") { value("1234") }
            }
    }

    @Test
    fun `should return the bank with the given account number`() {
        // given
        val accountNumber = 1234

        //when/then
        mockMvc.get("/api/banks/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.trust") { value("3.14") }
                jsonPath("$.transactionFee") { value("17") }
            }

    }


    @Test
    fun `should return Not Found if the account number does not exist`() {
        // given
        val accountNumber = 2323

        //when/then
        mockMvc.get("/api/banks/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `should add the new bank` () {
        // given
        val newBank = Bank("acc123", 31.415, 2)

        // when
        val performPost =  mockMvc.post("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBank)
        }

        // then
        performPost
            .andDo { print() }
            .andExpect {
                status { isCreated() }
            }

    }

    @Test
    fun `should return BAD REQUEST if bank with given account number is already exists` (){
        // given
        val invalidBank = Bank("1234", 1.0, 1)

        // when
        val performPost = mockMvc.post("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidBank)
        }

        // then
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

}


