package noStrings.semicolon.data.repositories;

import semicolon.noStrings.data.models.Message;

import java.util.List;

public interface MessageRepository {
    Message save(Message message);

    Message findById(int id);

    List<Message> findAll();

    void deleteById(int id);

    void deleteAll();

    int count();
}
