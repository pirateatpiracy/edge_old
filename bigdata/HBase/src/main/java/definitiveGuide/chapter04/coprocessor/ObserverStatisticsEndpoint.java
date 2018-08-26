package definitiveGuide.chapter04.coprocessor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.CoprocessorException;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.io.FSDataInputStreamWrapper;
import org.apache.hadoop.hbase.io.Reference;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.protobuf.ResponseConverter;
import org.apache.hadoop.hbase.regionserver.DeleteTracker;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.regionserver.KeyValueScanner;
import org.apache.hadoop.hbase.regionserver.MiniBatchOperationInProgress;
import org.apache.hadoop.hbase.regionserver.Region;
import org.apache.hadoop.hbase.regionserver.RegionScanner;
import org.apache.hadoop.hbase.regionserver.ScanType;
import org.apache.hadoop.hbase.regionserver.Store;
import org.apache.hadoop.hbase.regionserver.StoreFile;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionRequest;
import org.apache.hadoop.hbase.regionserver.wal.HLogKey;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.wal.WALKey;

import definitiveGuide.chapter04.coprocessor.generated.ObserverStatisticsProtos;

// cc ObserverStatisticsEndpoint Observer collecting invocation statistics.
// vv ObserverStatisticsEndpoint
@SuppressWarnings("deprecation") // because of API usage
public class ObserverStatisticsEndpoint
  extends ObserverStatisticsProtos.ObserverStatisticsService
  implements Coprocessor, CoprocessorService, RegionObserver {

  private RegionCoprocessorEnvironment env;
  private Map<String, Integer> stats = new LinkedHashMap<>();

  // Lifecycle methods

   
  public void start(CoprocessorEnvironment env) throws IOException {
    if (env instanceof RegionCoprocessorEnvironment) {
      this.env = (RegionCoprocessorEnvironment) env;
    } else {
      throw new CoprocessorException("Must be loaded on a table region!");
    }
  }

  /*...*/
  // ^^ ObserverStatisticsEndpoint
   
  public void stop(CoprocessorEnvironment env) throws IOException {
    // nothing to do when coprocessor is shutting down
  }

   
  public Service getService() {
    return this;
  }

  // vv ObserverStatisticsEndpoint
  // Endpoint methods

   
  public void getStatistics(RpcController controller,
    ObserverStatisticsProtos.StatisticsRequest request,
    RpcCallback<ObserverStatisticsProtos.StatisticsResponse> done) {
    ObserverStatisticsProtos.StatisticsResponse response = null;
    try {
      ObserverStatisticsProtos.StatisticsResponse.Builder builder =
        ObserverStatisticsProtos.StatisticsResponse.newBuilder();
      ObserverStatisticsProtos.NameInt32Pair.Builder pair =
        ObserverStatisticsProtos.NameInt32Pair.newBuilder();
      for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        pair.setName(entry.getKey());
        pair.setValue(entry.getValue().intValue());
        builder.addAttribute(pair.build());
      }
      response = builder.build();
      // optionally clear out stats
      if (request.hasClear() && request.getClear()) {
        synchronized (stats) {
          stats.clear();
        }
      }
    } catch (Exception e) {
      ResponseConverter.setControllerException(controller,
        new IOException(e));
    }
    done.run(response);
  }

  /**
   * Internal helper to keep track of call counts.
   *
   * @param call The name of the call.
   */
  private void addCallCount(String call) {
    synchronized (stats) {
      Integer count = stats.get(call);
      if (count == null) count = new Integer(1);
      else count = new Integer(count + 1);
      stats.put(call, count);
    }
  }

  // All Observer callbacks follow here

   
  public void preOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("preOpen");
  }

   
  public void postOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext) {
    addCallCount("postOpen");
  }

  /*...*/
  // ^^ ObserverStatisticsEndpoint
   
  public void postLogReplay(
    ObserverContext<RegionCoprocessorEnvironment> observerContext) {
    addCallCount("postLogReplay");
  }

   
  public InternalScanner preFlushScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    KeyValueScanner keyValueScanner, InternalScanner internalScanner)
    throws IOException {
    addCallCount("preFlushScannerOpen");
    return internalScanner;
  }

   
  public void preFlush(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("preFlush1");
  }

   
  public InternalScanner preFlush(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    InternalScanner internalScanner) throws IOException {
    addCallCount("preFlush2");
    return internalScanner;
  }

   
  public void postFlush(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("postFlush1");
  }

   
  public void postFlush(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    StoreFile storeFile) throws IOException {
    addCallCount("postFlush2");
  }

   
  public void preCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    List<StoreFile> list, CompactionRequest compactionRequest)
    throws IOException {
    addCallCount("preCompactSelection1");
  }

   
  public void preCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    List<StoreFile> list) throws IOException {
    addCallCount("preCompactSelection2");
  }

   
  public void postCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    ImmutableList<StoreFile> immutableList,
    CompactionRequest compactionRequest) {
    addCallCount("postCompactSelection1");
  }

   
  public void postCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    ImmutableList<StoreFile> immutableList) {
    addCallCount("postCompactSelection2");
  }

   
  public InternalScanner preCompact(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    InternalScanner internalScanner, ScanType scanType,
    CompactionRequest compactionRequest) throws IOException {
    addCallCount("preCompact1");
    return internalScanner;
  }

   
  public InternalScanner preCompact(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    InternalScanner internalScanner, ScanType scanType) throws IOException {
    addCallCount("preCompact2");
    return internalScanner;
  }

   
  public InternalScanner preCompactScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    List<? extends KeyValueScanner> list, ScanType scanType, long l,
    InternalScanner internalScanner, CompactionRequest compactionRequest)
    throws IOException {
    addCallCount("preCompactScannerOpen1");
    return internalScanner;
  }

   
  public InternalScanner preCompactScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    List<? extends KeyValueScanner> list, ScanType scanType, long l,
    InternalScanner internalScanner) throws IOException {
    addCallCount("preCompactScannerOpen2");
    return internalScanner;
  }

   
  public void postCompact(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    StoreFile storeFile, CompactionRequest compactionRequest)
    throws IOException {
    addCallCount("postCompact1");
  }

   
  public void postCompact(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    StoreFile storeFile) throws IOException {
    addCallCount("postCompact2");
  }

   
  public void preSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("preSplit1");
  }

   
  public void preSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes)
    throws IOException {
    addCallCount("preSplit2");
  }

   
  public void postSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Region region, Region region1) throws IOException {
    addCallCount("postSplit");
  }

   
  public void preSplitBeforePONR(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    List<Mutation> list) throws IOException {
    addCallCount("preSplitBeforePONR");
  }

   
  public void preSplitAfterPONR(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("preSplitAfterPONR");
  }

   
  public void preRollBackSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("preRollBackSplit");
  }

   
  public void postRollBackSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("postRollBackSplit");
  }

   
  public void postCompleteSplit(
    ObserverContext<RegionCoprocessorEnvironment> observerContext)
    throws IOException {
    addCallCount("postCompleteSplit");
  }

   
  public void preClose(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, boolean b)
    throws IOException {
    addCallCount("preClose");
  }

   
  public void postClose(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, boolean b) {
    addCallCount("postClose");
  }

   
  public void preGetClosestRowBefore(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, Result result) throws IOException {
    addCallCount("preGetClosestRowBefore");
  }

   
  public void postGetClosestRowBefore(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, Result result) throws IOException {
    addCallCount("postGetClosestRowBefore");
  }

   
  public void preGetOp(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
    List<Cell> list) throws IOException {
    addCallCount("preGetOp");
  }

   
  public void postGetOp(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
    List<Cell> list) throws IOException {
    addCallCount("postGetOp");
  }

   
  public boolean preExists(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
    boolean b) throws IOException {
    addCallCount("preExists");
    return b;
  }

   
  public boolean postExists(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
    boolean b) throws IOException {
    addCallCount("postExists");
    return b;
  }

   
  public void prePut(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Put put,
    WALEdit walEdit, Durability durability) throws IOException {
    addCallCount("prePut");
  }

   
  public void postPut(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Put put,
    WALEdit walEdit, Durability durability) throws IOException {
    addCallCount("postPut");
  }

   
  public void preDelete(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Delete delete, WALEdit walEdit, Durability durability) throws IOException {
    addCallCount("preDelete");
  }

   
  public void prePrepareTimeStampForDeleteVersion(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Mutation mutation, Cell cell, byte[] bytes, Get get) throws IOException {
    addCallCount("prePrepareTimeStampForDeleteVersion");
  }

   
  public void postDelete(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Delete delete, WALEdit walEdit, Durability durability) throws IOException {
    addCallCount("postDelete");
  }

   
  public void preBatchMutate(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress)
    throws IOException {
    addCallCount("preBatchMutate");
  }

   
  public void postBatchMutate(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress)
    throws IOException {
    addCallCount("postBatchMutate");
  }

   
  public void postStartRegionOperation(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    HRegion.Operation operation) throws IOException {
    addCallCount("postStartRegionOperation");
    addCallCount("- postStartRegionOperation-" + operation);
  }

   
  public void postCloseRegionOperation(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    HRegion.Operation operation) throws IOException {
    addCallCount("postCloseRegionOperation");
    addCallCount("- postCloseRegionOperation-" + operation);
  }

   
  public void postBatchMutateIndispensably(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress,
    boolean b) throws IOException {
    addCallCount("postBatchMutateIndispensably");
  }

   
  public boolean preCheckAndPut(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Put put, boolean b)
    throws IOException {
    addCallCount("preCheckAndPut");
    return b;
  }

   
  public boolean preCheckAndPutAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Put put, boolean b)
    throws IOException {
    addCallCount("preCheckAndPutAfterRowLock");
    return b;
  }

   
  public boolean postCheckAndPut(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Put put, boolean b)
    throws IOException {
    addCallCount("postCheckAndPut");
    return b;
  }

   
  public boolean preCheckAndDelete(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
    throws IOException {
    addCallCount("preCheckAndDelete");
    return b;
  }

   
  public boolean preCheckAndDeleteAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
    throws IOException {
    addCallCount("preCheckAndDeleteAfterRowLock");
    return b;
  }

   
  public boolean postCheckAndDelete(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, CompareFilter.CompareOp compareOp,
    ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
    throws IOException {
    addCallCount("postCheckAndDelete");
    return b;
  }

   
  public long preIncrementColumnValue(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, long l, boolean b) throws IOException {
    addCallCount("preIncrementColumnValue");
    return l;
  }

   
  public long postIncrementColumnValue(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, byte[] bytes,
    byte[] bytes1, byte[] bytes2, long l, boolean b, long l1)
    throws IOException {
    addCallCount("postIncrementColumnValue");
    return l;
  }

   
  public Result preAppend(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Append append) throws IOException {
    addCallCount("preAppend");
    return null;
  }

   
  public Result preAppendAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Append append) throws IOException {
    addCallCount("preAppendAfterRowLock");
    return null;
  }

   
  public Result postAppend(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Append append, Result result) throws IOException {
    addCallCount("postAppend");
    return result;
  }

   
  public Result preIncrement(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Increment increment) throws IOException {
    addCallCount("preIncrement");
    return null;
  }

   
  public Result preIncrementAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Increment increment) throws IOException {
    addCallCount("preIncrementAfterRowLock");
    return null;
  }

   
  public Result postIncrement(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    Increment increment, Result result) throws IOException {
    addCallCount("postIncrement");
    return result;
  }

   
  public RegionScanner preScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Scan scan,
    RegionScanner regionScanner) throws IOException {
    addCallCount("preScannerOpen");
    return regionScanner;
  }

   
  public KeyValueScanner preStoreScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Store store,
    Scan scan, NavigableSet<byte[]> navigableSet,
    KeyValueScanner keyValueScanner) throws IOException {
    addCallCount("preStoreScannerOpen");
    return keyValueScanner;
  }

   
  public RegionScanner postScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext, Scan scan,
    RegionScanner regionScanner) throws IOException {
    addCallCount("postScannerOpen");
    return regionScanner;
  }

   
  public boolean preScannerNext(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    InternalScanner internalScanner, List<Result> list, int i, boolean b)
    throws IOException {
    addCallCount("preScannerNext");
    return b;
  }

   
  public boolean postScannerNext(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    InternalScanner internalScanner, List<Result> list, int i, boolean b)
    throws IOException {
    addCallCount("postScannerNext");
    return b;
  }

   
  public boolean postScannerFilterRow(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    InternalScanner internalScanner, byte[] bytes, int i, short i1, boolean b)
    throws IOException {
    addCallCount("postScannerFilterRow");
    return b;
  }

   
  public void preScannerClose(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    InternalScanner internalScanner) throws IOException {
    addCallCount("preScannerClose");
  }

   
  public void postScannerClose(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    InternalScanner internalScanner) throws IOException {
    addCallCount("postScannerClose");
  }

   
  public void preWALRestore(
    ObserverContext<? extends RegionCoprocessorEnvironment> observerContext,
    HRegionInfo hRegionInfo, WALKey walKey, WALEdit walEdit)
    throws IOException {
    addCallCount("preWALRestore1");
  }

   
  public void preWALRestore(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    HRegionInfo hRegionInfo, HLogKey hLogKey, WALEdit walEdit)
    throws IOException {
    addCallCount("preWALRestore2");
  }

   
  public void postWALRestore(
    ObserverContext<? extends RegionCoprocessorEnvironment> observerContext,
    HRegionInfo hRegionInfo, WALKey walKey, WALEdit walEdit)
    throws IOException {
    addCallCount("postWALRestore1");
  }

   
  public void postWALRestore(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    HRegionInfo hRegionInfo, HLogKey hLogKey, WALEdit walEdit)
    throws IOException {
    addCallCount("postWALRestore2");
  }

   
  public void preBulkLoadHFile(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    List<Pair<byte[], String>> list) throws IOException {
    addCallCount("preBulkLoadHFile");
  }

   
  public boolean postBulkLoadHFile(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    List<Pair<byte[], String>> list, boolean b) throws IOException {
    addCallCount("postBulkLoadHFile");
    return b;
  }

   
  public StoreFile.Reader preStoreFileReaderOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    FileSystem fileSystem, Path path,
    FSDataInputStreamWrapper fsDataInputStreamWrapper, long l,
    CacheConfig cacheConfig, Reference reference, StoreFile.Reader reader)
    throws IOException {
    addCallCount("preStoreFileReaderOpen");
    addCallCount("- preStoreFileReaderOpen-" + path.getName());
    return reader;
  }

   
  public StoreFile.Reader postStoreFileReaderOpen(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    FileSystem fileSystem, Path path,
    FSDataInputStreamWrapper fsDataInputStreamWrapper, long l,
    CacheConfig cacheConfig, Reference reference, StoreFile.Reader reader)
    throws IOException {
    addCallCount("postStoreFileReaderOpen");
    addCallCount("- postStoreFileReaderOpen-" + path.getName());
    return reader;
  }

   
  public Cell postMutationBeforeWAL(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    MutationType mutationType, Mutation mutation, Cell cell, Cell cell1)
    throws IOException {
    addCallCount("postMutationBeforeWAL");
    addCallCount("- postMutationBeforeWAL-" + mutationType);
    return cell1;
  }

   
  public DeleteTracker postInstantiateDeleteTracker(
    ObserverContext<RegionCoprocessorEnvironment> observerContext,
    DeleteTracker deleteTracker) throws IOException {
    addCallCount("postInstantiateDeleteTracker");
    return deleteTracker;
  }
  // vv ObserverStatisticsEndpoint
}
// ^^ ObserverStatisticsEndpoint
