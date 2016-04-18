package org.codelibs.fess.dict.seunjeon;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.codelibs.core.lang.StringUtil;
import org.codelibs.fess.dict.DictionaryItem;

public class SeunjeonItem extends DictionaryItem {
    private final String[] inputs;

    private final String[] outputs;

    private String[] newInputs;

    private String[] newOutputs;

    public SeunjeonItem(final long id, final String[] inputs, final String[] outputs) {
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
        Arrays.sort(inputs);
        if (inputs != outputs) {
            Arrays.sort(outputs);
        }

        if (id == 0) {
            // create
            newInputs = inputs;
            newOutputs = outputs;
        }
    }

    public String[] getNewInputs() {
        return newInputs;
    }

    public void setNewInputs(final String[] newInputs) {
        this.newInputs = newInputs;
    }

    public String[] getNewOutputs() {
        return newOutputs;
    }

    public void setNewOutputs(final String[] newOutputs) {
        this.newOutputs = newOutputs;
    }

    public String[] getInputs() {
        return inputs;
    }

    public String getInputsValue() {
        if (inputs == null) {
            return StringUtil.EMPTY;
        }
        return String.join("\n", inputs);
    }

    public String[] getOutputs() {
        return outputs;
    }

    public String getOutputsValue() {
        if (outputs == null) {
            return StringUtil.EMPTY;
        }
        return String.join("\n", outputs);
    }

    public boolean isUpdated() {
        return newInputs != null && newOutputs != null;
    }

    public boolean isDeleted() {
        return isUpdated() && newInputs.length == 0;
    }

    public void sort() {
        if (inputs != null) {
            Arrays.sort(inputs);
        }
        if (outputs != null) {
            Arrays.sort(outputs);
        }
        if (newInputs != null) {
            Arrays.sort(newInputs);
        }
        if (newOutputs != null) {
            Arrays.sort(newOutputs);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(inputs);
        result = prime * result + Arrays.hashCode(outputs);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SeunjeonItem other = (SeunjeonItem) obj;
        sort();
        other.sort();
        if (!Arrays.equals(inputs, other.inputs)) {
            return false;
        }
        if (!Arrays.equals(outputs, other.outputs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SynonymItem [id=" + id + ", inputs=" + Arrays.toString(inputs) + ", outputs=" + Arrays.toString(outputs) + ", newInputs="
                + Arrays.toString(newInputs) + ", newOutputs=" + Arrays.toString(newOutputs) + "]";
    }

    public String toLineString() {
        if (isUpdated()) {
            if (Arrays.equals(newInputs, newOutputs)) {
                return StringUtils.join(newInputs, ",");
            } else {
                return StringUtils.join(newInputs, ",") + "=>" + StringUtils.join(newOutputs, ",");
            }
        } else {
            if (Arrays.equals(inputs, outputs)) {
                return StringUtils.join(inputs, ",");
            } else {
                return StringUtils.join(inputs, ",") + "=>" + StringUtils.join(outputs, ",");
            }
        }
    }


}