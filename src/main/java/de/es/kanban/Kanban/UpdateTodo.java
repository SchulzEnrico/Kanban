package de.es.kanban.Kanban;

public record UpdateTodo(
                            String description,
                            TodoStatus status ) {
}
