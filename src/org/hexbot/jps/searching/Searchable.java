package org.hexbot.jps.searching;

import org.hexbot.jps.model.Node;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kyle Stevenson
 *         Date: 6/9/13
 *         Time: 5:50 PM
 */
public interface Searchable {
    public Node[] findPath();
}
