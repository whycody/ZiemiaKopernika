package pl.ziemiakopernika.ziemiakopernika.dao;

import java.util.ArrayList;
import java.util.Collections;

import pl.ziemiakopernika.ziemiakopernika.model.Answer;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public class QuestionsDaoImpl implements QuestionsDao{

    @Override
    public ArrayList<Question> getRandomQustions(int number) {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Który księżyc jako jedyny w Układzie Słonecznym posiada gęstą atmosferę?", "Tytan", "Księżyc", "Io", "Europa"));
        questions.add(new Question("Jądro której galaktyki znajduje się najbliżej Układu Słonecznego?", "Karzeł Wielkiego Psa", "Karzeł Strzelca", "Wielki Obłok Magellana", "Droga Mleczna"));
        questions.add(new Question("Jaką średnicę ma Mars?", "6794 km", "6894 km", "6994 km", "6694 km"));
        questions.add(new Question("Fobos jest księżycem której planety?", "Marsa", "Uranu", "Saturna", "Jowisza"));
        questions.add(new Question("Co było pierwszą udaną misją planetarną NASA?", "Zbliżenie się do Wenus", "Zbliżenie się do Słońca", "Zbliżenie się do Plutona", "Zbliżenie się do Marsa"));
        Collections.reverse(questions);
        return questions; //TODO retrieve Questions data from Database
    }

    @Override
    public ArrayList<Answer> getRandomAnswers(int number) {
        ArrayList<Answer> answers = new ArrayList<>();
        for(int i =1; i<=number; i++){
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(0);
            integers.add(1);
            integers.add(2);
            integers.add(3);
            Collections.reverse(integers);
            answers.add(new Answer(0, integers));
        }
        return answers;
    }

}
