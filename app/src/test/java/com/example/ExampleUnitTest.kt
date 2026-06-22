package com.example

import com.example.data.ExcelHelper
import com.example.data.Expense
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ExampleUnitTest {
  @Test
  fun testExcelExportAndImport() {
    val expenses = listOf(
      Expense(
        id = 1,
        amount = 120.50,
        description = "Lunch",
        category = "Food",
        date = System.currentTimeMillis()
      ),
      Expense(
        id = 2,
        amount = 45.00,
        description = "Bus fare",
        category = "Transport",
        date = System.currentTimeMillis()
      )
    )

    val outputStream = ByteArrayOutputStream()
    ExcelHelper.exportExpenses(outputStream, expenses)

    val byteArray = outputStream.toByteArray()
    assertTrue(byteArray.isNotEmpty())

    val inputStream = ByteArrayInputStream(byteArray)
    val importedExpenses = ExcelHelper.importExpenses(inputStream)

    assertEquals(2, importedExpenses.size)
    assertEquals(120.50, importedExpenses[0].amount, 0.01)
    assertEquals("Lunch", importedExpenses[0].description)
    assertEquals("Food", importedExpenses[0].category)

    assertEquals(45.00, importedExpenses[1].amount, 0.01)
    assertEquals("Bus fare", importedExpenses[1].description)
    assertEquals("Transport", importedExpenses[1].category)
  }
}
