package de.es.kanban.Kanban;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {
    private final TodoRepo todoRepo;
    private final IdService idService;

    public TodoService( TodoRepo todoRepo, IdService idService ) {
        this.todoRepo = todoRepo;
        this.idService = idService;
    }

   public List<Todo> findAllTodos() {
       return todoRepo.findAll();
   }

   public Todo addTodo( NewTodo newTodo ) {
        String id = idService.randomId();

        Todo TodoToSave = new Todo(
                                    id,
                                    newTodo.description(),
                                    newTodo.status()
                                    );

        return todoRepo.save( TodoToSave );
    }

    public Todo findTodoById( String id ) {
        return todoRepo.findById( id )
                .orElseThrow(() -> new NoSuchElementException( "Todo with id: " + id + " not found!" ));
    }

    public Todo updateTodo( UpdateTodo todo, String id ) {
        Todo todoToUpdate = new Todo(
                                        id,
                                        todo.description(),
                                        todo.status()
                                        );

        return todoRepo.save( todoToUpdate );
    }

    public void deleteTodo( String id ) {
        todoRepo.deleteById( id );
    }
}

