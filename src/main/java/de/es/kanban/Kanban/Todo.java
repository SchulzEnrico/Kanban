package de.es.kanban.Kanban;

public record Todo(
                    String id,
                    String description,
                    TodoStatus status ) {
}
