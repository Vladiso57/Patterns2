package com.Labs.Patterns.dao;

import java.util.List;

public interface IRoleDao {
    List<String> getRoles(String nickname);
}
