package definitiveGuide.chapter08.coprocessor;

import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.NavigableSet;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;

import org.apache.hadoop.conf.Configuration;
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

import definitiveGuide.chapter08.coprocessor.generated.ScanControlProtos;

// cc ScanControlObserverEndpoint Observer and endpoint for scan operations
// vv ScanControlObserverEndpoint
@SuppressWarnings("deprecation") // because of API usage
public class ScanControlObserverEndpoint
  extends ScanControlProtos.ScanControlService
  implements Coprocessor, CoprocessorService, RegionObserver {

  private RegionCoprocessorEnvironment env;
  private BitSet stopRows = new BitSet();

  // Lifecycle methods

   
  public void start(CoprocessorEnvironment env) throws IOException {
    if (env instanceof RegionCoprocessorEnvironment) {
      this.env = (RegionCoprocessorEnvironment) env;
      Configuration conf = env.getConfiguration();
      String rows = conf.get("com.larsgeorge.copro.stoprows", "5");
      for (String row : rows.split(",")) {
        stopRows.set(Integer.parseInt(row));
      }
    } else {
      throw new CoprocessorException("Must be loaded on a table region!");
    }
  }

   
  public void stop(CoprocessorEnvironment env) throws IOException {
    // nothing to do when coprocessor is shutting down
  }

   
  public Service getService() {
    return this;
  }

  // Endpoint methods

   
  public void resumeScan(RpcController controller,
    ScanControlProtos.ScanControlRequest request,
    RpcCallback<ScanControlProtos.ScanControlResponse> done) {
    ScanControlProtos.ScanControlResponse response = null;
    try {
      response = ScanControlProtos.ScanControlResponse.getDefaultInstance();
    } catch (Exception e) {
      ResponseConverter.setControllerException(controller, new IOException(e));
    }
    done.run(response);
  }

  // RegionObserver Methods

   
  public RegionScanner preScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Scan scan, RegionScanner s)
    throws IOException {
    return null;
  }

   
  public boolean preScannerNext(ObserverContext<RegionCoprocessorEnvironment> c,
    InternalScanner s, List<Result> result, int limit, boolean hasNext)
    throws IOException {
    return false;
  }

   
  public boolean postScannerNext(
    ObserverContext<RegionCoprocessorEnvironment> c, InternalScanner s,
    List<Result> result, int limit, boolean hasNext) throws IOException {
    return false;
  }

  // RegionObserver Stubs

   
  public void preOpen(ObserverContext<RegionCoprocessorEnvironment> c)
    throws IOException {

  }

   
  public void postOpen(ObserverContext<RegionCoprocessorEnvironment> c) {

  }

   
  public void postLogReplay(ObserverContext<RegionCoprocessorEnvironment> c) {

  }

   
  public InternalScanner preFlushScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    KeyValueScanner memstoreScanner, InternalScanner s) throws IOException {
    return null;
  }

   
  public void preFlush(ObserverContext<RegionCoprocessorEnvironment> c)
    throws IOException {

  }

   
  public InternalScanner preFlush(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    InternalScanner scanner) throws IOException {
    return null;
  }

   
  public void postFlush(ObserverContext<RegionCoprocessorEnvironment> c)
    throws IOException {

  }

   
  public void postFlush(ObserverContext<RegionCoprocessorEnvironment> c,
    Store store, StoreFile resultFile) throws IOException {

  }

   
  public void preCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    List<StoreFile> candidates, CompactionRequest request) throws IOException {

  }

   
  public void preCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    List<StoreFile> candidates) throws IOException {

  }

   
  public void postCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    ImmutableList<StoreFile> selected, CompactionRequest request) {

  }

   
  public void postCompactSelection(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    ImmutableList<StoreFile> selected) {

  }

   
  public InternalScanner preCompact(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    InternalScanner scanner, ScanType scanType, CompactionRequest request)
    throws IOException {
    return null;
  }

   
  public InternalScanner preCompact(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    InternalScanner scanner, ScanType scanType) throws IOException {
    return null;
  }

   
  public InternalScanner preCompactScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    List<? extends KeyValueScanner> scanners, ScanType scanType,
    long earliestPutTs, InternalScanner s, CompactionRequest request)
    throws IOException {
    return null;
  }

   
  public InternalScanner preCompactScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store,
    List<? extends KeyValueScanner> scanners, ScanType scanType,
    long earliestPutTs, InternalScanner s) throws IOException {
    return null;
  }

   
  public void postCompact(ObserverContext<RegionCoprocessorEnvironment> c,
    Store store, StoreFile resultFile, CompactionRequest request)
    throws IOException {

  }

   
  public void postCompact(ObserverContext<RegionCoprocessorEnvironment> c,
    Store store, StoreFile resultFile) throws IOException {

  }

   
  public void preSplit(ObserverContext<RegionCoprocessorEnvironment> c)
    throws IOException {

  }

   
  public void preSplit(ObserverContext<RegionCoprocessorEnvironment> c,
    byte[] splitRow) throws IOException {

  }

   
  public void postSplit(ObserverContext<RegionCoprocessorEnvironment> c,
    Region l, Region r) throws IOException {

  }

   
  public void preSplitBeforePONR(
    ObserverContext<RegionCoprocessorEnvironment> ctx, byte[] splitKey,
    List<Mutation> metaEntries) throws IOException {

  }

   
  public void preSplitAfterPONR(
    ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {

  }

   
  public void preRollBackSplit(
    ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {

  }

   
  public void postRollBackSplit(
    ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {

  }

   
  public void postCompleteSplit(
    ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {

  }

   
  public void preClose(ObserverContext<RegionCoprocessorEnvironment> c,
    boolean abortRequested) throws IOException {

  }

   
  public void postClose(ObserverContext<RegionCoprocessorEnvironment> c,
    boolean abortRequested) {

  }

   
  public void preGetClosestRowBefore(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    Result result) throws IOException {

  }

   
  public void postGetClosestRowBefore(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    Result result) throws IOException {

  }

   
  public void preGetOp(ObserverContext<RegionCoprocessorEnvironment> c, Get get,
    List<Cell> result) throws IOException {

  }

   
  public void postGetOp(ObserverContext<RegionCoprocessorEnvironment> c,
    Get get, List<Cell> result) throws IOException {

  }

   
  public boolean preExists(ObserverContext<RegionCoprocessorEnvironment> c,
    Get get, boolean exists) throws IOException {
    return false;
  }

   
  public boolean postExists(ObserverContext<RegionCoprocessorEnvironment> c,
    Get get, boolean exists) throws IOException {
    return false;
  }

   
  public void prePut(ObserverContext<RegionCoprocessorEnvironment> c, Put put,
    WALEdit edit, Durability durability) throws IOException {

  }

   
  public void postPut(ObserverContext<RegionCoprocessorEnvironment> c, Put put,
    WALEdit edit, Durability durability) throws IOException {

  }

   
  public void preDelete(ObserverContext<RegionCoprocessorEnvironment> c,
    Delete delete, WALEdit edit, Durability durability) throws IOException {

  }

   
  public void prePrepareTimeStampForDeleteVersion(
    ObserverContext<RegionCoprocessorEnvironment> c, Mutation mutation,
    Cell cell, byte[] byteNow, Get get) throws IOException {

  }

   
  public void postDelete(ObserverContext<RegionCoprocessorEnvironment> c,
    Delete delete, WALEdit edit, Durability durability) throws IOException {

  }

   
  public void preBatchMutate(ObserverContext<RegionCoprocessorEnvironment> c,
    MiniBatchOperationInProgress<Mutation> miniBatchOp) throws IOException {

  }

   
  public void postBatchMutate(ObserverContext<RegionCoprocessorEnvironment> c,
    MiniBatchOperationInProgress<Mutation> miniBatchOp) throws IOException {

  }

   
  public void postStartRegionOperation(
    ObserverContext<RegionCoprocessorEnvironment> ctx,
    HRegion.Operation operation) throws IOException {

  }

   
  public void postCloseRegionOperation(
    ObserverContext<RegionCoprocessorEnvironment> ctx,
    HRegion.Operation operation) throws IOException {

  }

   
  public void postBatchMutateIndispensably(
    ObserverContext<RegionCoprocessorEnvironment> ctx,
    MiniBatchOperationInProgress<Mutation> miniBatchOp, boolean success)
    throws IOException {

  }

   
  public boolean preCheckAndPut(ObserverContext<RegionCoprocessorEnvironment> c,
    byte[] row, byte[] family, byte[] qualifier,
    CompareFilter.CompareOp compareOp, ByteArrayComparable comparator, Put put,
    boolean result) throws IOException {
    return false;
  }

   
  public boolean preCheckAndPutAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, CompareFilter.CompareOp compareOp,
    ByteArrayComparable comparator, Put put, boolean result)
    throws IOException {
    return false;
  }

   
  public boolean postCheckAndPut(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, CompareFilter.CompareOp compareOp,
    ByteArrayComparable comparator, Put put, boolean result)
    throws IOException {
    return false;
  }

   
  public boolean preCheckAndDelete(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, CompareFilter.CompareOp compareOp,
    ByteArrayComparable comparator, Delete delete, boolean result)
    throws IOException {
    return false;
  }

   
  public boolean preCheckAndDeleteAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, CompareFilter.CompareOp compareOp,
    ByteArrayComparable comparator, Delete delete, boolean result)
    throws IOException {
    return false;
  }

   
  public boolean postCheckAndDelete(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, CompareFilter.CompareOp compareOp,
    ByteArrayComparable comparator, Delete delete, boolean result)
    throws IOException {
    return false;
  }

   
  public long preIncrementColumnValue(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, long amount, boolean writeToWAL) throws IOException {
    return 0;
  }

   
  public long postIncrementColumnValue(
    ObserverContext<RegionCoprocessorEnvironment> c, byte[] row, byte[] family,
    byte[] qualifier, long amount, boolean writeToWAL, long result)
    throws IOException {
    return 0;
  }

   
  public Result preAppend(ObserverContext<RegionCoprocessorEnvironment> c,
    Append append) throws IOException {
    return null;
  }

   
  public Result preAppendAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> c, Append append)
    throws IOException {
    return null;
  }

   
  public Result postAppend(ObserverContext<RegionCoprocessorEnvironment> c,
    Append append, Result result) throws IOException {
    return null;
  }

   
  public Result preIncrement(ObserverContext<RegionCoprocessorEnvironment> c,
    Increment increment) throws IOException {
    return null;
  }

   
  public Result preIncrementAfterRowLock(
    ObserverContext<RegionCoprocessorEnvironment> c, Increment increment)
    throws IOException {
    return null;
  }

   
  public Result postIncrement(ObserverContext<RegionCoprocessorEnvironment> c,
    Increment increment, Result result) throws IOException {
    return null;
  }

   
  public KeyValueScanner preStoreScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Store store, Scan scan,
    NavigableSet<byte[]> targetCols, KeyValueScanner s) throws IOException {
    return null;
  }

   
  public RegionScanner postScannerOpen(
    ObserverContext<RegionCoprocessorEnvironment> c, Scan scan, RegionScanner s)
    throws IOException {
    return null;
  }

   
  public boolean postScannerFilterRow(
    ObserverContext<RegionCoprocessorEnvironment> c, InternalScanner s,
    byte[] currentRow, int offset, short length, boolean hasMore)
    throws IOException {
    return false;
  }

   
  public void preScannerClose(ObserverContext<RegionCoprocessorEnvironment> c,
    InternalScanner s) throws IOException {

  }

   
  public void postScannerClose(ObserverContext<RegionCoprocessorEnvironment> c,
    InternalScanner s) throws IOException {

  }

   
  public void preWALRestore(
    ObserverContext<? extends RegionCoprocessorEnvironment> ctx,
    HRegionInfo info, WALKey logKey, WALEdit logEdit) throws IOException {

  }

   
  public void preWALRestore(ObserverContext<RegionCoprocessorEnvironment> ctx,
    HRegionInfo info, HLogKey logKey, WALEdit logEdit) throws IOException {

  }

   
  public void postWALRestore(
    ObserverContext<? extends RegionCoprocessorEnvironment> ctx,
    HRegionInfo info, WALKey logKey, WALEdit logEdit) throws IOException {

  }

   
  public void postWALRestore(ObserverContext<RegionCoprocessorEnvironment> ctx,
    HRegionInfo info, HLogKey logKey, WALEdit logEdit) throws IOException {

  }

   
  public void preBulkLoadHFile(
    ObserverContext<RegionCoprocessorEnvironment> ctx,
    List<Pair<byte[], String>> familyPaths) throws IOException {

  }

   
  public boolean postBulkLoadHFile(
    ObserverContext<RegionCoprocessorEnvironment> ctx,
    List<Pair<byte[], String>> familyPaths, boolean hasLoaded)
    throws IOException {
    return false;
  }

   
  public StoreFile.Reader preStoreFileReaderOpen(
    ObserverContext<RegionCoprocessorEnvironment> ctx, FileSystem fs, Path p,
    FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r,
    StoreFile.Reader reader) throws IOException {
    return null;
  }

   
  public StoreFile.Reader postStoreFileReaderOpen(
    ObserverContext<RegionCoprocessorEnvironment> ctx, FileSystem fs, Path p,
    FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r,
    StoreFile.Reader reader) throws IOException {
    return null;
  }

   
  public Cell postMutationBeforeWAL(
    ObserverContext<RegionCoprocessorEnvironment> ctx, MutationType opType,
    Mutation mutation, Cell oldCell, Cell newCell) throws IOException {
    return null;
  }

   
  public DeleteTracker postInstantiateDeleteTracker(
    ObserverContext<RegionCoprocessorEnvironment> ctx, DeleteTracker delTracker)
    throws IOException {
    return null;
  }


  // vv ScanControlObserverEndpoint
}
// ^^ ScanControlObserverEndpoint
