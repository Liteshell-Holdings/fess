package org.codelibs.fess.dict.seunjeon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codelibs.core.lang.StringUtil;
import org.codelibs.fess.Constants;
import org.codelibs.fess.dict.DictionaryException;
import org.codelibs.fess.dict.DictionaryFile;
import org.dbflute.optional.OptionalEntity;

public class SeunjeonFile extends DictionaryFile<SeunjeonItem> {
    private static final String SEUNJEON = "seunjeon";

    List<SeunjeonItem> seunjeonItemList;

    public SeunjeonFile(final String id, final String path, final Date timestamp) {
        super(id, path, timestamp);
    }

    @Override
    public String getType() {
        return SEUNJEON;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public synchronized OptionalEntity<SeunjeonItem> get(final long id) {
        if (seunjeonItemList == null) {
            reload(null, null);
        }

        for (final SeunjeonItem SeunjeonItem : seunjeonItemList) {
            if (id == SeunjeonItem.getId()) {
                return OptionalEntity.of(SeunjeonItem);
            }
        }
        return OptionalEntity.empty();
    }

    @Override
    public synchronized PagingList<SeunjeonItem> selectList(final int offset, final int size) {
        if (seunjeonItemList == null) {
            reload(null, null);
        }

        if (offset >= seunjeonItemList.size() || offset < 0) {
            return new PagingList<SeunjeonItem>(Collections.<SeunjeonItem> emptyList(), offset, size, seunjeonItemList.size());
        }

        int toIndex = offset + size;
        if (toIndex > seunjeonItemList.size()) {
            toIndex = seunjeonItemList.size();
        }

        return new PagingList<SeunjeonItem>(seunjeonItemList.subList(offset, toIndex), offset, size, seunjeonItemList.size());
    }

    @Override
    public synchronized void insert(final SeunjeonItem item) {
        try (SynonymUpdater updater = new SynonymUpdater(item)) {
            reload(updater, null);
        }
    }

    @Override
    public synchronized void update(final SeunjeonItem item) {
        try (SynonymUpdater updater = new SynonymUpdater(item)) {
            reload(updater, null);
        }
    }

    @Override
    public synchronized void delete(final SeunjeonItem item) {
        final SeunjeonItem SeunjeonItem = item;
        SeunjeonItem.setNewInputs(StringUtil.EMPTY_STRINGS);
        SeunjeonItem.setNewOutputs(StringUtil.EMPTY_STRINGS);
        try (SynonymUpdater updater = new SynonymUpdater(item)) {
            reload(updater, null);
        }
    }

    protected void reload(final SynonymUpdater updater, final InputStream in) {
        final List<SeunjeonItem> itemList = new ArrayList<SeunjeonItem>();
        try (BufferedReader reader =
                new BufferedReader(new InputStreamReader(in != null ? in : dictionaryManager.getContentInputStream(this), Constants.UTF_8))) {
            long id = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0 || line.charAt(0) == '#') {
                    if (updater != null) {
                        updater.write(line);
                    }
                    continue; // ignore empty lines and comments
                }

                String inputs[];
                String outputs[];

                final List<String> sides = split(line, "=>");
                if (sides.size() > 1) { // explicit mapping
                    if (sides.size() != 2) {
                        throw new DictionaryException("more than one explicit mapping specified on the same line");
                    }
                    final List<String> inputStrings = split(sides.get(0), ",");
                    inputs = new String[inputStrings.size()];
                    for (int i = 0; i < inputs.length; i++) {
                        inputs[i] = unescape(inputStrings.get(i)).trim();
                    }

                    final List<String> outputStrings = split(sides.get(1), ",");
                    outputs = new String[outputStrings.size()];
                    for (int i = 0; i < outputs.length; i++) {
                        outputs[i] = unescape(outputStrings.get(i)).trim();
                    }

                    if (inputs.length > 0 && outputs.length > 0) {
                        id++;
                        final SeunjeonItem item = new SeunjeonItem(id, inputs, outputs);
                        if (updater != null) {
                            final SeunjeonItem newItem = updater.write(item);
                            if (newItem != null) {
                                itemList.add(newItem);
                            } else {
                                id--;
                            }
                        } else {
                            itemList.add(item);
                        }
                    }
                } else {
                    final List<String> inputStrings = split(line, ",");
                    inputs = new String[inputStrings.size()];
                    for (int i = 0; i < inputs.length; i++) {
                        inputs[i] = unescape(inputStrings.get(i)).trim();
                    }

                    if (inputs.length > 0) {
                        id++;
                        final SeunjeonItem item = new SeunjeonItem(id, inputs, inputs);
                        if (updater != null) {
                            final SeunjeonItem newItem = updater.write(item);
                            if (newItem != null) {
                                itemList.add(newItem);
                            } else {
                                id--;
                            }
                        } else {
                            itemList.add(item);
                        }
                    }
                }
            }
            if (updater != null) {
                final SeunjeonItem item = updater.commit();
                if (item != null) {
                    itemList.add(item);
                }
            }
            seunjeonItemList = itemList;
        } catch (final IOException e) {
            throw new DictionaryException("Failed to parse " + path, e);
        }
    }

    private static List<String> split(final String s, final String separator) {
        final List<String> list = new ArrayList<String>(2);
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        final int end = s.length();
        while (pos < end) {
            if (s.startsWith(separator, pos)) {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
                pos += separator.length();
                continue;
            }

            char ch = s.charAt(pos++);
            if (ch == '\\') {
                sb.append(ch);
                if (pos >= end) {
                    break; // ERROR, or let it go?
                }
                ch = s.charAt(pos++);
            }

            sb.append(ch);
        }

        if (sb.length() > 0) {
            list.add(sb.toString());
        }

        return list;
    }

    private String unescape(final String s) {
        if (s.indexOf('\\') >= 0) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                final char ch = s.charAt(i);
                if (ch == '\\' && i < s.length() - 1) {
                    sb.append(s.charAt(++i));
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        return s;
    }

    public String getSimpleName() {
        return new File(path).getName();
    }

    public InputStream getInputStream() throws IOException {
        return new BufferedInputStream(dictionaryManager.getContentInputStream(this));
    }

    public synchronized void update(final InputStream in) throws IOException {
        try (SynonymUpdater updater = new SynonymUpdater(null)) {
            reload(updater, in);
        }
    }

    @Override
    public String toString() {
        return "SynonymFile [path=" + path + ", seunjeonItemList=" + seunjeonItemList + ", id=" + id + "]";
    }

    protected class SynonymUpdater implements Closeable {

        protected boolean isCommit = false;

        protected File newFile;

        protected Writer writer;

        protected SeunjeonItem item;

        protected SynonymUpdater(final SeunjeonItem newItem) {
            try {
                newFile = File.createTempFile(SEUNJEON, ".txt");
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), Constants.UTF_8));
            } catch (final IOException e) {
                if (newFile != null) {
                    newFile.delete();
                }
                throw new DictionaryException("Failed to write a userDict file.", e);
            }
            item = newItem;
        }

        public SeunjeonItem write(final SeunjeonItem oldItem) {
            try {
                if (item != null && item.getId() == oldItem.getId() && item.isUpdated()) {
                    if (item.equals(oldItem)) {
                        try {
                            if (!item.isDeleted()) {
                                // update
                                writer.write(item.toLineString());
                                writer.write(Constants.LINE_SEPARATOR);
                                return new SeunjeonItem(item.getId(), item.getNewInputs(), item.getNewOutputs());
                            } else {
                                return null;
                            }
                        } finally {
                            item.setNewInputs(null);
                            item.setNewOutputs(null);
                        }
                    } else {
                        throw new DictionaryException("Seunjeon file was updated: old=" + oldItem + " : new=" + item);
                    }
                } else {
                    writer.write(oldItem.toLineString());
                    writer.write(Constants.LINE_SEPARATOR);
                    return oldItem;
                }
            } catch (final IOException e) {
                throw new DictionaryException("Failed to write: " + oldItem + " -> " + item, e);
            }
        }

        public void write(final String line) {
            try {
                writer.write(line);
                writer.write(Constants.LINE_SEPARATOR);
            } catch (final IOException e) {
                throw new DictionaryException("Failed to write: " + line, e);
            }
        }

        public SeunjeonItem commit() {
            isCommit = true;
            if (item != null && item.isUpdated()) {
                try {
                    writer.write(item.toLineString());
                    writer.write(Constants.LINE_SEPARATOR);
                    return item;
                } catch (final IOException e) {
                    throw new DictionaryException("Failed to write: " + item, e);
                }
            }
            return null;
        }

        @Override
        public void close() {
            try {
                writer.flush();
            } catch (final IOException e) {
                // ignore
            }
            IOUtils.closeQuietly(writer);

            if (isCommit) {
                try {
                    dictionaryManager.store(SeunjeonFile.this, newFile);
                } finally {
                    newFile.delete();
                }
            } else {
                newFile.delete();
            }
        }
    }
}