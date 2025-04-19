package com.ti.takehome.grpc;

import com.ti.takehome.persistence.entity.Todo;
import com.ti.takehome.service.TodoService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class TodoGrpcServiceImpl extends TodoServiceGrpc.TodoServiceImplBase {

    private final TodoService todoService;

    public TodoGrpcServiceImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void getTodo(TodoRequest request, StreamObserver<TodoData> responseObserver) {
        todoService.getTodoById(request.getId())
            .ifPresentOrElse(todo -> {
                responseObserver.onNext(toProto(todo));
                responseObserver.onCompleted();
            }, () -> responseObserver.onError(new RuntimeException("Todo not found")));
    }

    @Override
    public void getAllTodos(Empty request, StreamObserver<GetAllTodosResponse> responseObserver) {
        var todos = todoService.getAllTodos().stream()
            .map(this::toProto)
            .collect(Collectors.toList());

        GetAllTodosResponse response = GetAllTodosResponse.newBuilder()
            .addAllTodos(todos)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createTodo(CreateTodoRequest request, StreamObserver<TodoData> responseObserver) {
//        Todo todo = new Todo(request.getDescription(), request.getCompleted());
//        Todo saved = todoService.createTodo(todo);
//        responseObserver.onNext(toProto(saved));
//        responseObserver.onCompleted();
    }

    @Override
    public void updateTodo(UpdateTodoRequest request, StreamObserver<TodoData> responseObserver) {
        Todo updated = new Todo(request.getId(), request.getDescription(), request.getCompleted());
        todoService.updateTodo(request.getId(), updated)
            .ifPresentOrElse(todo -> {
                responseObserver.onNext(toProto(todo));
                responseObserver.onCompleted();
            }, () -> responseObserver.onError(new RuntimeException("Update failed")));
    }

    @Override
    public void deleteTodo(TodoRequest request, StreamObserver<DeleteTodoResponse> responseObserver) {
        boolean deleted = todoService.deleteTodo(request.getId());
        responseObserver.onNext(DeleteTodoResponse.newBuilder().setSuccess(deleted).build());
        responseObserver.onCompleted();
    }

    private TodoData toProto(Todo todo) {
        return TodoData.newBuilder()
            .setId(todo.getId())
            .setDescription(todo.getDescription())
            .setCompleted(todo.isCompleted())
            .build();
    }
}