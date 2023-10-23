package de.es.kanban.Kanban;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private TodoRepo todoRepo = mock(TodoRepo.class);

    private IdService idService = mock(IdService.class);

    private TodoService todoService = new TodoService(todoRepo, idService);


    @Test
    void addTodo() {
                    //GIVEN
                    NewTodo newTodoToSave = new NewTodo("TestAdd", TodoStatus.OPEN);
                    Todo expected = new Todo("TestId", "TestAdd", TodoStatus.OPEN);
                    //WHEN
                    when(todoRepo.save(any(Todo.class))).thenReturn(expected);
                    Todo actual = todoService.addTodo(newTodoToSave);
                    //THEN
                    verify(todoRepo).save(any(Todo.class));
                    assertEquals(expected, actual);
                }

    @Test
    void findAllTodos() {
                        //GIVEN
                        List<Todo> expected = List.of(
                                new Todo("1", "d1", TodoStatus.OPEN),
                                new Todo("2", "d2", TodoStatus.OPEN),
                                new Todo("3", "d3", TodoStatus.OPEN)
                        );

                        when(todoRepo.findAll()).thenReturn(expected);
                        //WHEN
                        List<Todo> actual = todoService.findAllTodos();
                        //THEN
                        verify(todoRepo).findAll();
                        assertEquals(expected, actual);
                    }

    @Test
    void updateTodo() {
                      //Given
                        String id = "321";
                        UpdateTodo todoToUpdate = new UpdateTodo("TestUpdate", TodoStatus.OPEN);
                        Todo updatedTodo = new Todo(id, "TestUpdate", TodoStatus.OPEN);

                        when(todoRepo.findById(id)).thenReturn(Optional.of(updatedTodo));
                        when(todoRepo.save(updatedTodo)).thenReturn(updatedTodo);

                        //WHEN
                        Todo actual = todoService.updateTodo(todoToUpdate, id);

                        //THEN
                        verify(todoRepo).save(updatedTodo);
                        assertEquals(updatedTodo, actual);
                    }

    @Test
    void getTodoByIdTest_whenValidId_ThenReturnTodo() {
        //GIVEN
        String id = "1";
        Todo todo = new Todo("1", "test-description", TodoStatus.OPEN);

        when(todoRepo.findById(id)).thenReturn(Optional.of(todo));

        //WHEN

        Todo actual = todoService.findTodoById(id);

        //THEN
        verify(todoRepo).findById(id);
        assertEquals(todo, actual);
    }

    @Test
    void getTodoByIdTest_whenInvalidId_ThenThrowException() {
        //GIVEN
        String id = "1";
        when(todoRepo.findById(id)).thenReturn(Optional.empty());

        //WHEN
        assertThrows(NoSuchElementException.class, () -> todoService.findTodoById(id));

        //THEN
        verify(todoRepo).findById(id);
    }

    @Test
    void deleteTodo() {
        //GIVEN
        String id = "1";
        doNothing().when(todoRepo).deleteById(id);

        //WHEN

        todoService.deleteTodo(id);

        //THEN
        verify(todoRepo).deleteById(id);
    }
}