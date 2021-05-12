# Add Mars to system partition

Refer to
```
https://android-review.googlesource.com/c/platform/frameworks/base/+/1692925

https://android-review.googlesource.com/c/platform/build/+/1690669

https://android-review.googlesource.com/c/device/google/cuttlefish/+/1690534
```

# Testing

## atest

Please refer to https://source.android.com/compatibility/tests/development/atest.

### Adding MarsTests to instrumentation_test_list
```
https://android-review.googlesource.com/c/platform/platform_testing/+/1705111
```

### Add the annotation @VisibleForTesting to the method you want to test.
```
    @VisibleForTesting
    public void start() {
      mIsStarted = true;
      mApn.start();
    }

    @VisibleForTesting
    public boolean isStarted() {
      return mIsStarted;
    }
```

### Execute the tests
```
  $ source build/envsetup.sh
  $ lunch sdk_phone_x86_64
  $ atest MarsTests
```

## gtest

Please refer to https://source.android.com/compatibility/tests/development/native and https://github.com/google/googletest.

To run the tests, run_tests.py under android/external/googletest can be used.
However, the tests should be located under /data/nativetest64/gtest_tests in advance.

```
    if args.host:
        test_location = os.path.join(os.environ['ANDROID_HOST_OUT'], 'nativetest64')
    else:
        data_dir = os.path.join(os.environ['OUT'], 'data')
        test_location = os.path.join(data_dir, 'nativetest64')
        if not os.path.exists(test_location):
            test_location = os.path.join(data_dir, 'nativetest')

    num_tests = 0
    failures = []
    for test_dir in ['gtest_tests', 'gtest_ndk_tests', 'gmock_tests']:
        test_dir = os.path.join(test_location, test_dir)
        if not os.path.isdir(test_dir):
            logger().debug('Skipping %s', test_dir)
            continue
```
