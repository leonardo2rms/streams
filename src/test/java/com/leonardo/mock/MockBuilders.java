package com.leonardo.mock;

import com.leonardo.model.User;

import java.util.Arrays;
import java.util.List;

public class MockBuilders {

    protected List<User> buildUsers() {
        return Arrays.asList(
                new User("leo", Arrays.asList(1, 2, 4)),
                new User("je", Arrays.asList(3, 2, 5))
        );
    }

}
