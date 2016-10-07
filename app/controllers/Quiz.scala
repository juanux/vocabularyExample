package controllers

import play.api.i18n.Lang
import play.api.mvc._
import javax.inject.Inject
import services.VocabularyService
import models.Vocabulary

class Quiz @Inject() (vocabulary: VocabularyService) extends Controller {

  def quiz(sourceLanguage: Lang, targetLanguage: Lang) = Action { request =>

    vocabulary.findRandomVocabulary(sourceLanguage, targetLanguage).map { v =>
      println(v.word)
      Ok(v.word)

    }.getOrElse {

      NotFound

    }

  }

  def check(sourceLanguage: Lang, word: String, targetLanguage: Lang, traslation: String) = Action { request =>

    val correct = vocabulary.verify(sourceLanguage, word, targetLanguage, traslation)

    if (correct) {
      Ok
    } else {
      NotAcceptable
    }

  }

}
