/*
 * Copyright (c) 2010-2015 Osman Shoukry
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License or any
 *    later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.openpojo.issues.issue33.sample;

/**
 * @author oshoukry
 */
public class ClassAggregatingAbstractClass {
  private AbstractClass abstractClass;

  public void setAbstractClass(AbstractClass abstractClass) {
    this.abstractClass = abstractClass;
  }

  public AbstractClass getAbstractClass() {
    return this.abstractClass;
  }
}
