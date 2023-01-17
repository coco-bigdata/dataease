package io.tortoise.provider;

/**
 * @Author gin
 * @Date 2021/5/17 4:19 下午
 */
public abstract class DDLProvider {
    public abstract String createView(String name, String viewSQL);

    public abstract String dropTable(String name);

    public abstract String dropView(String name);
}
