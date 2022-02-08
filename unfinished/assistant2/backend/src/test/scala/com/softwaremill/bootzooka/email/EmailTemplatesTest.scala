package com.softwaremill.bootzooka.email

import org.scalatest.{FlatSpec, Matchers}

class EmailTemplatesTest extends FlatSpec with Matchers {
  val templates = new EmailTemplates

  it should "generate the registration confirmation email" in {
    // when
    val email = templates.registrationConfirmation("john")

    // then
    email.subject should be("Ассистент - подтверждение регистрации для пользователя john")
    email.content should include("john,")
    email.content should include("С уважением,")
  }
}
