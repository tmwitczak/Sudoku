///////////////////////////////////////////////////////////////////// Package //
package jptw.sudoku;


///////////////////////////////////////////////////////////////////// Imports //
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


////////////////////////////////////////////////////////// Class: SudokuField //
class SudokuField
        implements Cloneable,
                   Comparable<SudokuField>,
                   Serializable {

    //=========================================================== Behaviour ==//
    //------------------------------------------------------ Constructors --==//
    public
    SudokuField() {

        this(0, false);
    }

    public
    SudokuField(final int value) {

        this(value, false);
    }

    public
    SudokuField(final int value,
                final boolean isMutable) {

        setFieldValue(value);
        setFieldMutability(isMutable);
    }


    //-------------------------------------------------------------- Accessors <
    public
    int getFieldValue() {

        return value;
    }

    public
    boolean getFieldMutability() {
        
        return isMutable;
    }

    public
    void setFieldValue(final int value) {

        if ((value < 0) || (value > 9)) {

            throw new InvalidValueException("value: "
                                            + value
                                            + " should be within the range"
                                              + " of [0, 9]!");
        }

        this.value = value;
    }

    public
    void setFieldMutability(final boolean isMutable) {

        this.isMutable = isMutable;
    }


    //-------------------------------------------------------- Comparison --==//
    @Override
    public
    boolean equals(final Object object) {

        if (object == this) {

            return true;
        }

        if ((object == null)
            ||
            (object.getClass() != getClass())) {

            return false;
        }

        SudokuField field
            = (SudokuField) object;

        return new EqualsBuilder()
                   .append(this.value,
                           field.value)
                   .append(this.isMutable,
                           field.isMutable)
                   .isEquals();
    }

    @Override
    public
    int hashCode() {

        return new HashCodeBuilder(11, 17)
                           .append(this.value)
                           .append(this.isMutable)
                           .toHashCode();
    }

    @Override
    public
    String toString() {

        return new ToStringBuilder(this,
                                   ToStringStyle.SIMPLE_STYLE)
                           .append("value",
                                   this.value)
                           .append("isMutable",
                                   this.isMutable)
                           .toString();
    }

    @Override
    public
    int compareTo(final SudokuField o) {

        if (this.getFieldValue() == o.getFieldValue()) {

            return 0;

        } else if (this.getFieldValue() > o.getFieldValue()) {

            return 1;

        } else {

            return -1;
        }
    }

    @Override
    protected
    Object clone() {

        return new SudokuField(value, isMutable);
    }


    //================================================================ Data ==//
    private
    int value;

    private
    boolean isMutable;


}


////////////////////////////////////////////////////////////////////////////////

