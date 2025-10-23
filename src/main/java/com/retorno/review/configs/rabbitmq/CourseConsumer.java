package com.retorno.review.configs.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumer {

    @Autowired
    private CourseService courseService;

    // O consumidor não precisa necessariamente salvar tudo no banco; ele processa o que precisa.
    // Você salva quando:
    //  O consumidor precisa manter histórico ou estado próprio (por exemplo: registrar todos os cursos recebidos, gerar relatórios, auditoria).
    //  Você quer que o consumidor tenha dados independentes do produtor.

    @RabbitListener(queues = "queue_reviewms")
    public void receiveMessage(Course course) {
        System.out.println("Mensagem recebida: " + course);

        Course saved = courseService.saveCourse(course);
        System.out.println("Curso salvo no banco: " + saved);
    }
}


