package com.xignitex.usecase;

public interface UseCase<R, P> {
    P execute(R request);
}
