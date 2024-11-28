package com.xignitex.usecase;

public interface UseCase <Request, Response>{
    Response execute (Request request);
}
