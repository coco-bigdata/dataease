```xml
<execution>
    <id>npm install</id>
    <goals>
        <goal>npm</goal>
    </goals>
    <!-- Optional configuration which provides for running any npm command -->
    <configuration>
        <arguments>install --force</arguments>
    </configuration>
</execution>
```